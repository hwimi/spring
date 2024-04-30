console.log("board register.js");

document.getElementById('trigger').addEventListener('click',()=>{
    document.getElementById('file').click();
});

//정규표현식을 사용하여 파일의 형식을 제한
//실행파일 막기(exe,bat,sh,mis,dll,jar)
//파일사이즈 체크(20mb 보다 작게 크면 막기)
//이미지 파일만 올리기(jpg.jpeg,gif,png,bmp)

const regExp=new RegExp("\.(exe|sh|bat|mis|dll|jar)$");
const regExpImg=new RegExp("\.(jpg|jpeg|gif|png|bmo)$");
const maxSize=1024*1024*20;

//validation: 규칙설정
//return 0 / 1 로 설정
function fileValidation(name,fileSize){
    let fileName=name.toLowerCase();//파일 이름을 전부 소문자로 변경
    if(regExp.test(fileName)){ //파일 확장자에 실행파일 확장자가 있다면 0으로
        return 0;
    }else if(fileSize>maxSize){
        return 0;
    }else if(!regExpImg.test(fileName)){
        return 0;
    }else{
        return 1;
    }
}
//첨부파일에 따라 등록이 가능한지 체크 함수
document.addEventListener('change',(e)=>{
    console.log(e.target);
    if(e.target.id==='file'){

        //여러개의 파일이 배열로 들어옴
        const fileObject=document.getElementById('file').files;
        console.log(fileObject);
        //한번 true 가 된 disabled 는 다시 false 로 돌아올수 없음. 버튼 활성화.
        document.getElementById('regBtn').disabled=false; //비활성화


        let div=document.getElementById('fileZone');
        div.innerHTML='';//기존에 등록한 파일이 있다면 지우기

        let ul=`<ul class="list-group">`;
        //각각의 파일은 fileValidation 의해 리턴 여부를 체크
        //모든 파일의 return의 값이 1이어야 가능
        //*로 isOk를 처리하면 모든 파일이 1인지 체크
        let isOk=1;
        for(let file of fileObject){
            let validResult=fileValidation(file.name,file.size); //하나의 파일에 대한 결과
            isOk*=validResult;
            ul+=` <li class="list-group-item">`;
            ul+=`<div>${validResult? '업로드가능':'업로드불가능'}</div>${file.name}`;
            ul+=`<span class="badge text-bg-${validResult? 'success':'danger'}">${file.size}</span>`
            ul+=`</li>`
        }
        ul+=`</ul>`
        div.innerHTML=ul;
        //업로드한 불가능한 파일이 1개라도 있으면 버튼을 비활성화
        if(isOk==0){
            document.getElementById('regBtn').disabled=true; //비활성화
        }
    }
})

/*
<ul class="list-group">
  <li class="list-group-item"><div>업로드 가능</div>파일이름
    <span class="badge text-bg-success">파일사이즈</span>
  </li>
  <li class="list-group-item"><div>업로드 불가능</div>파일이름
    <span class="badge text-bg-danger">파일 사이즈</span>
   </li>
</ul>
*/
