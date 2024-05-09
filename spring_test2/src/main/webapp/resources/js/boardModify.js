console.log("하이");

document.addEventListener('click',(e)=>{
     if(e.target.classList.contains('file-X')){
         let uuid=e.target.dataset.uuid;
         console.log(uuid);
         removeFileSever(uuid).then(result=>{
             if(result==1){
                 alert("파일삭제");
                 e.target.closest('li').remove();
             }
         })

    }
})



async function removeFileSever(uuid){
   try {
    const url="/board/file/"+uuid;
    const config={
        method:"delete"
    }
        const resp=await fetch(url,config);
        const result=resp.text();
        return result;    
   } catch (error) {
    console.log("error")
   }
}

