package Dao.Post;

import Dto.Post.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;
import java.util.Optional;

@Repository
public class JDBCPostDao implements PostDao{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int postInsert(PostDto post) throws RuntimeException {
        try{
            String sql = "" +
                    "INSERT INTO `post`\n" +
                    "       ( title\n" +
                    "       , author_id\n" +
                    "       , content\n" +
                    "       , written_date\n" +
                    "       , last_modified_date\n)" +
                    "VALUES (?\n" +
                    "     , (SELECT id FROM `user` WHERE `name` = ?)\n" +
                    "     , ?, ?, ?);";

            KeyHolder key = new GeneratedKeyHolder();

            if(1 == jdbcTemplate.update(connection -> {
                    PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
                    ps.setString(1, post.getTitle());
                    ps.setString(2, post.getAuthor());
                    ps.setString(3, post.getContent());
                    ps.setTimestamp(4, Timestamp.valueOf(post.getWrittenDate()));
                    ps.setTimestamp(5, Optional.ofNullable(post.getLastModifiedDate())
                            .map(lmd -> Timestamp.valueOf(lmd))
                            .orElse(null));

                    return ps;
                }, key)){
                return key.getKey().intValue();
            }
            else {
                throw new RuntimeException("DAO : 게시글 삽입 실패");
            }
        } catch(DataAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("DAO : 게시글 삽입 실패");
        }
    }

    @Override
    public boolean postDelete(int id) throws RuntimeException {
        try {
            String sql = "" +
                    "DELETE\n" +
                    "  FROM `post`\n" +
                    " WHERE `id` = ?;";
            if(1 == this.jdbcTemplate.update(sql
                    , id)) {
                return true;
            }
            else {
                throw new RuntimeException("DAO : 게시글 삭제 실패");
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("DAO : 게시글 삭제 실패");
        }

    }

    @Override
    public int postUpdate(PostDto post) throws RuntimeException{
        try {
            String sql = "" +
                    "UPDATE `post`\n" +
                    "   SET `title` = ?\n" +
                    "     , `content` = ?\n" +
                    "     , `last_modified_date` = ?\n" +
                    " WHERE `id` = ?;";
            if(1 == this.jdbcTemplate.update(sql
                    , post.getTitle()
                    , post.getContent()
                    , Timestamp.valueOf(post.getLastModifiedDate())
                    , post.getId())) {
                return post.getId();
            }
            else {
                throw new RuntimeException("DAO: 게시글 갱신 실패");
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("DAO: 게시글 갱신 실패");
        }
    }

    @Override
    public PostDto postSelect(int id) throws RuntimeException {
        try {
            String sql = "" +
                    "SELECT p.id AS id" +
                    "     , p.title AS title" +
                    "     , u.name AS author" +
                    "     , p.written_date AS written_date" +
                    "     , p.last_modified_date AS last_modified_date" +
                    "     , p.content AS content" +
                    "  FROM `post` AS p" +
                    "  LEFT OUTER" +
                    "  JOIN `user` AS u" +
                    "    ON u.id = p.author_id" +
                    " WHERE p.id = ?;";

            PostDto result = this.jdbcTemplate.queryForObject(
                    sql,
                    new RowMapper<PostDto>() {
                        @Override
                        public PostDto mapRow(ResultSet rs, int i) throws SQLException {
                            PostDto post = new PostDto(
                                    rs.getInt("id"),
                                    rs.getString("title"),
                                    rs.getString("author"),
                                    rs.getTimestamp("written_date").toLocalDateTime(),
                                    Optional.ofNullable(rs.getTimestamp("last_modified_date")).map(
                                            ts -> ts.toLocalDateTime()
                                    ).orElse(null),
                                    rs.getString("content")
                            );
                            return post;
                        }
                    },
                    id
            );
            if(result == null) {
                throw new RuntimeException("DAO: 게시글 조회 실패");
            }
            return result;
        } catch (DataAccessException e) {
            throw new RuntimeException("DAO: 게시글 조회 실패");
        }
    }

    @Override
    public int postCount() throws RuntimeException {
        try {
            String sql = "" +
                    "SELECT COUNT(*) AS 'count'\n" +
                    "  FROM `post`;";
            return jdbcTemplate.queryForObject(sql, Integer.class);
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("총 게시글 수 조회 실패");
        }
    }

    @Override
    public int latestIdSelect() {
        try {
            String sql = "" +
                    "SELECT MAX(id) AS latest_id" +
                    "  FROM `post`;";
            return jdbcTemplate.queryForObject(sql, Integer.class);
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("최근 ID 조회 실패");
        }
    }

    @Override
    public List<PostDto> postsSelect(int startPage, int amount) {
        try {
            String sql = "" +
                    "SELECT p.id AS id" +
                    "     , p.title AS title" +
                    "     , u.name AS author" +
                    "     , p.written_date AS written_date" +
                    "     , p.last_modified_date AS last_modified_date" +
                    "     , p.content AS content" +
                    "  FROM `post` AS p" +
                    "  LEFT OUTER" +
                    "  JOIN `user` AS u" +
                    "    ON u.id = p.author_id" +
                    " ORDER BY `id` DESC" +
                    " LIMIT ?, ?;";

            List<PostDto> result = jdbcTemplate.query(
                    sql,
                    new Object[]{startPage, amount},
                    new RowMapper<PostDto>() {
                        public PostDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                            PostDto p = new PostDto(
                                    rs.getInt("id"),
                                    rs.getString("title"),
                                    rs.getString("author"),
                                    rs.getTimestamp("written_date").toLocalDateTime(),
                                    Optional.ofNullable(rs.getTimestamp("last_modified_date")).map(
                                            ts -> ts.toLocalDateTime()
                                    ).orElse(null),
                                    rs.getString("Content")
                            );
                            return p;
                        }
                    }
            );
            if(result == null) {
                throw new RuntimeException("게시글s 조회 실패");
            }
            return result;
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("게시글s 조회 실패");
        }
    }

}
