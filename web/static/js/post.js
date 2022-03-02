const axios = window.axios;
const deleteBtn = document.querySelector("#DeleteButton");

deleteBtn.addEventListener('click', (e) => {
    e.preventDefault();
    const url = "/posts";
    const params = new URLSearchParams();
    params.append('_targetPostId', deleteBtn.getAttribute('targetPostId'));
    console.log(params);
    const config = {
        headers: {
            'Content-Type' : "application/x-www-form-urlencoded; charset=UTF-8;"
        }
    };
    axios.defaults.headers.delete = null;
    axios.delete(
        url,
        {
            data: params,
            headers: {
                'Content-Type' : "application/x-www-form-urlencoded; charset=UTF-8;"
            }
        }
    ).then(res => {
            console.log(res);
            if(res.data.error) {
                alert("게시글 삭제에 실패했습니다!");
            }
            window.location = res.data.redirection;
        })
});

const updateBtn = document.querySelector("#UpdateButton");

updateBtn.addEventListener('click', e => {
    e.preventDefault();
    axios.get("/posts/form/update/" + updateBtn.getAttribute('targetPostId'))
        .then(res=> {
            console.log(res);
            document.open();
            document.write(res.data);
            document.close();
        });
});