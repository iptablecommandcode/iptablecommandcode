/*
Auth : 박치원
title : 전화번호 양식 정렬
Desc :  전화번호를 (2)-(4)-(4),(3)-(3)-(4),(2)-(3)-(4),(3)-(4)-(4) 자리에 숫자 그룹을 지어 묶어준다.
*/
function PhoneNumberSetting(Number){
    if(Number.length == 10) {
        if(Number.substring(0,1) == '02'){
            return Number.replace(/^(\d{2})(\d{4})(\d{4})$/, `$1-$2-$3`);
        } else {
            return Number.replace(/^(\d{3})(\d{3})(\d{4})$/, `$1-$2-$3`);
        }
    } else if (Number.length == 9) {
        return Number.replace(/^(\d{2})(\d{3})(\d{4})$/, `$1-$2-$3`);
    } else {
        return Number.replace(/^(\d{3})(\d{4})(\d{4})$/, `$1-$2-$3`);
    }

}
/*
Auth : 박치원
title : Get전송
Desc : form으로 데이터 전송시 get방식으로 데이터를 전송한다.
*/
function submitGetAction(form, action, name, id){
    console.log("submitGetAction");

    form.id = id;
    form.name = name;
    form.method = 'GET';
    form.action = action;
    form.submit();

    document.getElementById("form").remove();
}
/*
Auth : 박치원
title : Post전송
Desc : form으로 데이터 전송시 Post방식으로 데이터를 전송한다.
*/
function submitPostAction(form, action, name, id){
    console.log("submitPostAction");

    form.id = id;
    form.name = name;
    form.method = 'POST';
    form.action = action;
    form.submit();

    document.getElementById("form").remove();
}
/*
Auth : 박치원
title : input 값 생성 (hidden)
Desc : 화면에서 input, hidden 값을 생성할때 사용한다.
*/
function inputValue(name, value){
    console.log("name : " + name);
    console.log("value : " + value);

    var input = document.createElement("input");

    input.setAttribute("type","hidden");
    input.setAttribute("name", name);
    input.setAttribute("value", value);

    return input;
}
/*
Auth : 박치원
title : input 값 생성 (Password)
Desc : 화면에서 input, Password 값을 생성할때 사용한다.
*/
function inputPasswordValue(name, value){
    console.log("name : " + name);
    console.log("value : " + value);

    var input = document.createElement("input");

    input.setAttribute("type","password");
    input.setAttribute("name", name);
    input.setAttribute("value", value);

    return input;
}
/*
Auth : 박치원
title : input 값 생성 (Email)
Desc : 화면에서 input, Email 값을 생성할때 사용한다.
*/
function inputEmailValue(name, value){
    console.log("name : " + name);
    console.log("value : " + value);

    var input = document.createElement("input");

    input.setAttribute("type","email");
    input.setAttribute("name", name);
    input.setAttribute("value", value);

    return input;
}
function createForm(){
    console.log("createForm");
    var form = document.createElement("form");
    document.body.appendChild(form);
    return form;
}