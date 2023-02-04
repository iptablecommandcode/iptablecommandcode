/*
Auth : 박치원
title : 전화번호 양식 정렬
Desc :  000-0000-0000 전화번호를 (3)-(4)-(4) 자리에 숫자 그룹을 지어 묶어준다.
*/
function PhoneNumberSetting(Number){
    return Number.replace(/^(\d{3})(\d{4})(\d{4})$/, `$1-$2-$3`);
}
/*
Auth : 박치원
title : Get전송
Desc : form으로 데이터 전송시 get방식으로 데이터를 전송한다.
*/
function submitGetAction(form, action, name, id){
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
function inputValue(value){
    var input = document.createElement("input");

    input.setAttribute("type","hidden");
    input.setAttribute("name","id");
    input.setAttribute("value", value);

    return input;
}
/*
Auth : 박치원
title : input 값 생성 (Password)
Desc : 화면에서 input, Password 값을 생성할때 사용한다.
*/
function inputPasswordValue(value){
    var input = document.createElement("input");

    input.setAttribute("type","password");
    input.setAttribute("name","id");
    input.setAttribute("value", value);

    return input;
}
/*
Auth : 박치원
title : input 값 생성 (Email)
Desc : 화면에서 input, Email 값을 생성할때 사용한다.
*/
function inputEmailValue(value){
    var input = document.createElement("input");

    input.setAttribute("type","email");
    input.setAttribute("name","id");
    input.setAttribute("value", value);

    return input;
}