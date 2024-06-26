console.log("확인");
//트리거 버튼 처리
document.getElementById('trigger').addEventListener('click',()=>{
    document.getElementById('file').click();
});

//실행파일에 대한 정규표현식 / 파일 최대 사이즈
const regExp=new RegExp("\.(exe|sh|bat|jar|dll|msi)$")
const maxSize=1024*1024*20;

function fileValidation(fileName,fileSize){
    if(regExp.test(fileName)){
        return 0;
    }else if(fileSize>maxSize){
        return 0;
    }else{
        return 1;
    }
}
document.addEventListener('change',(e)=>{
    if(e.target.id=='file'){ //파일에 변화가 생겼다며..
        //input type="file" element 에 저장된 file 정보를 가져오는 property
        const fileObj=document.getElementById('file').files;
        //스스로 안된다
        document.getElementById('regBtn').disabled=false;
        console.log(fileObj)
        //등록된 file의 정보를 fileZone에 기록
        let div=document.getElementById('fileZone');
        div.innerHTML='';
        //ul>li로 파일 목록 추가
        // <ul class="list-group list-group-flush">
        // <li class="list-group-item">An item</li>
        //여러개의 등록파일이 모두 검증을 통과해야하기 때문에
        //isOk*로 각각 파일이 통과할때마다 연산을 실행
        let isOk=1;
        let ul=`<ul class="list-group list-group-flush">`;
        for(let file of fileObj){
            let vailResult=fileValidation(file.name,file.size);
                isOk*=vailResult;
                ul+=`<li class="list-group-item">`;
                ul+=`<div class="mb-3">`;
                ul += `${vailResult ? `<div class="fw-bold">업로드가능</div>` : `<div class="fw-bold">불가능</div>`}`;
                ul+=`${file.name}</div>`
                ul+=`<span class="badge rounded-pill text-bg-${vailResult?'success':'danger'}">${file.size}</span>`
                ul+=`<li>`
                }
                ul+=`</ul>`;
                div.innerHTML=ul;
                if(isOk==0){
                    document.getElementById('regBtn').disabled=true; //비활성화
                }
        
    }
})