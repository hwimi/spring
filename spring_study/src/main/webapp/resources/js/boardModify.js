console.log("연결확인");


document.addEventListener('click',(e)=>{
    if(e.target.classList.contains('file-X')){
        let uuid=e.target.dataset.uuid;
        console.log(uuid);
        removeFiveServer(uuid).then(result=>{
            if(result==1){
                alert('파일삭제!!')
                e.target.closest('li').remove();
            }
        })

        
    }
})






async function removeFiveServer(uuid){
    try {
        const url="/board/"+uuid;
        const config={
            method:'delete'
        }
        const resp=await fetch(url, config);
        const result=resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}