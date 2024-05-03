console.log("boardDetailComment.js");

//cmtAddBtn  버튼을 누르면 bno writer content 를 비동기로 db에넣기
document.getElementById('cmtAddBtn').addEventListener('click',()=>{
    const cmtText=document.getElementById('cmtText').value; //input 은 value 값을주러
    const cmtWriter=document.getElementById('cmtWriter').innerText; //태그 사이에 있는 innerText
    if(cmtText==null||cmtText==''){
        alert("댓글을 입력해주세요");
        document.getAnimations('cmtText').focus();
        return false;
    }else{
        let cmtData={
            bno:bnoVal,
            writer:cmtWriter,
            content:cmtText
            
        };
        console.log(cmtData)
        postCommentServer(cmtData).then(result=>{
            if(result=='1'){
                alert("댓글등록성공")
                document.getElementById('cmtText').value="";
                spreadCommentList(bnoVal);
            }
        })
    }
    

})

function spreadCommentList(bno,page=1){
    getCommentListFromServer(bno,page).then(result=>{
        console.log(result);
        //댓글 뿌리기
        const ul=document.getElementById('cmtListArea');
        if(result.cmtList.length>0){
            if(page==1){
                //page 1이면 뿌려줘
                ul.innerHTML='';
            }

            for(let cvo of result.cmtList){
                let li=`<li class="list-group-item" data-cno="${cvo.cno}">`;
                li+=`<div class="input-group mb-3">no.${cvo.cno}:`;
                li+=`<div class="fw-bold">${cvo.writer}</div>`
                li+=`${cvo.content}`;
                li+=`</div>`;
                li+=`<span class="badge rounded-pill text-bg-warning">${cvo.regDate}</span>`;
                //수정삭제버튼
                li+=`<button type="button" class="btn btn-outline-warning btn-sm mod " data-bs-toggle="modal" data-bs-target="#myModal">수정</button>`
                li+=`<button type="button" data-cno="${cvo.cno}" class="btn btn-outline-danger btn-sm del">삭제</button>`
                li+=`</li>`
                ul.innerHTML+=li;

            }
            //더보기 버튼
            let moreBtn=document.getElementById('moreBtn');
            console.log(moreBtn);
            //moreBtn 표시되는 조건
            //pgvo.pageNo=1 /realEndPage=3
            //realEndPage 보다 현재 내페이지가 작으면 표시
            if(result.pgvo.pageNo<result.realEndPage){
                //style="visibility:hidden"=숨김 visibility:visible=표시
                moreBtn.style.visibility='visible'; //버튼 표시
                moreBtn.dataset.page=page+1;
            }else{
                moreBtn.style.visibility='hidden';//숨김
            }

                
        }else{
            let li=`<li class="list-group-item">댓글이 없습니다</li>`
            il.innerHTML=li;
        }
    })
}


document.addEventListener('click',(e)=>{
    if(e.target.id=='moreBtn'){
        let page=parseInt(e.target.dataset.page);
        spreadCommentList(bnoVal,page);
    }
    //수정시 모달창을 통해 댓글 입력받기
    else if(e.target.classList.contains('mod')){
        //closest 제일 가까운 //내가 수정버튼을 누른 댓글의 li
        let li=e.target.closest('li');
        //nextSibling:한 부모 안에서 다음 형제를 찾기
        let cmtText=li.querySelector('.fw-bold').nextSibling;
        console.log(cmtText);
        document.getElementById('cmtTextMod').value=cmtText.nodeValue;

        //수정=>cno dataset으로 달기 cno,content
        document.getElementById('cmtModBtn').setAttribute("data-cno",li.dataset.cno)
    }else if(e.target.id=='cmtModBtn'){
        let cmtModata={
            cno:e.target.dataset.cno,
            content:document.getElementById('cmtTextMod').value
        };
        console.log(cmtModata);
        //비동기로 보내기
        updateCommentServer(cmtModata).then(result=>{
            if(result=='1'){
                alert("댓글수정")
                document.getElementById('cmtText').value="";
                spreadCommentList(bnoVal);
            }
           
        })

        
    }
    else if(e.target.classList.contains('del')){
        // let cnoVal=e.target.dataset.cno;
        let li=e.target.closest('li');
        let cnoVal=li.dataset.cno;
        console.log(cnoVal)
        //비동기로 삭제요청
        removerCommentServer(cnoVal).then(result=>{
            console.log(result)
            if(result=="1"){
                alert("댓글삭제 성공");
                spreadCommentList(bnoVal);
            }
        })
     
    }
})







async function postCommentServer(cmtData){
    try {
        const url="/comment/post" //post 삽입
        const config={
            method:"post",
            headers:{
                "content-type":"application/json; charset=utf-8"
            },
            body:JSON.stringify(cmtData)
        };
        const resp=await fetch(url,config);
        const result=await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

async function getCommentListFromServer(bno,page){
    try {
        const resp=await fetch("/comment/"+bno+"/"+page)
        const result=await resp.json(); //객체
        return result;
    } catch (error) {
        console.log(error);
    }
}

async function updateCommentServer(cmtData){
try {
    const url="/comment/modify"
    const config={
        method:"put",
        headers:{
            'Content-type':'application/json; charset=utf-8'
        },
        body:JSON.stringify(cmtData)
    }
    const resp=await fetch(url,config)
    const result=await resp.text();
    return result;
} catch (error) {
    console.log(error)
}
}
async function removerCommentServer(cnoVal){
    try {
        const url="/comment/"+cnoVal;
        const config={
            method:"delete"
        }
        const resp=await fetch(url,config)
        const result=await resp.text();
        return result;
    } catch (error) {
        console.log(error)
    }
}









