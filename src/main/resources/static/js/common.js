// params에 저장한 메소드를 서버로 전송하는 메소드 이다.
// getParameter : actionLink : String, formMethod : String, params : key,value
function data_Action(url, method, params){
    var form = document.createElement('form');

    //메소드 액션셋 설정
    form.setAttribute('method', method);
    form.setAttribute('action', url);

    if (params == null){
        params = null;
    } else {
        // 데이터를 key,value로 저장한다.
        for (var key in params) {
            var hiddenField = document.createElement('input');
            hiddenField.setAttribute('type', 'hidden');
            hiddenField.setAttribute('name', key);
            hiddenField.setAttribute('value', params[key]);
            form.appendChild(hiddenField);
        }
    }

    document.body.appendChild(form);

    form.submit();//전송
}

// 각각의 데이터를 받아 JSON방식으로 변경하는 메소드
// getParameter : Arr_changeData : Array
function ch_JSON(Arr_changeData){
    var change_Arr = new Array();
    change_Arr.push(Arr_changeData);

    var fin_JSON = JSON.stringify(change_Arr);

    return fin_JSON;
}

// index페이지로 넘어가도록 요청하는 페이지 이다. 세션 값이 있을경우 인덱스에 접속 가능하지만 세션 없을경우 로그인 페이지로 전환된다.
function main_Page(){
    var url = '/';
    var method = 'POST';

    data_Action(url,method, null);
}

// 전화번호 정리 -(하이픈)추가
function Tel_Sort(Tel){
    var total_Tel = null;

    if(Tel.length == 12) {
        //12자리 번호 Sorting
        // 010 번호 Sorting
        if (Tel.substr(0, 4) == '0100') {
            total_Tel = '010';
            total_Tel += Tel_Back_Num_Sort(Tel);
        } else if (Tel.substr(0, 4) == '0110') {// 011 번호 Sorting
            total_Tel = '011';
            total_Tel += Tel_Back_Num_Sort(Tel);
        }

    } else if (Tel.length == 11){
        //11자리 번호 Sorting
        total_Tel = Tel.substr(0,3);
        total_Tel += Tel_Back_Num_Sort(Tel);
    }
    return total_Tel;
}

// 전화번호 뒷 8자리 정렬
function Tel_Back_Num_Sort(back_Tel){
    var return_back_tel = null;
    if (back_Tel.length == 12){
        return_back_tel = '-';
        return_back_tel += back_Tel.substr(4, 4);
        return_back_tel += '-';
        return_back_tel += back_Tel.substr(8, 4);
    }else if (back_Tel.length == 11){
        return_back_tel = '-';
        return_back_tel += back_Tel.substr(3, 4);
        return_back_tel += '-';
        return_back_tel += back_Tel.substr(7, 4);
    }
    return return_back_tel;
}
// 정규식 룰 체크 참(ID, PW, NAME)(조건 값이 참일경우 정상 처리)
function Rule_Check_True_Rule(chkStr, regExp, elementID){

    if (regExp.test(chkStr)){
        document.getElementById(elementID).className = "form-control is-valid";
    } else if(chkStr == ''){
        document.getElementById(elementID).className = "form-control";
    } else {
        document.getElementById(elementID).className = "form-control is-invalid";
    }
}

// 정규식 이름,주소 체크(NAME, ADDRESS)(조건 값이 참일경우 비정상 처리)
function Rule_Check_False_Rule(chkStr, regExp, elementID){

    if (regExp.test(chkStr)){
        document.getElementById(elementID).className = "form-control is-invalid";
    } else if(chkStr == ''){
        document.getElementById(elementID).className = "form-control";
    } else {
        document.getElementById(elementID).className = "form-control is-valid";
    }
}