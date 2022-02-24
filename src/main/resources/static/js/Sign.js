// 로그인시 ID,PW를 dataset에 저장하는 메소드 이다.
function Sign_In(){
    var url = '/Sign/Sign_In.do';
    var method = 'POST';

    var userAccount = new Object();

    userAccount.ID = document.getElementById('ID').value;
    userAccount.PW = document.getElementById('PW').value;

    data_Action(url, method, userAccount);
}

// zipcode 호출 api 스크립트
function Address(){
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var roadAddr = data.roadAddress; // 도로명 주소 변수
            var extraRoadAddr = ''; // 추가 정보 변수

            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                extraRoadAddr += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if(data.buildingName !== '' && data.apartment === 'Y'){
                extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if(extraRoadAddr !== ''){
                extraRoadAddr = ' (' + extraRoadAddr + ')';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('ZIPCODE').value = data.zonecode;
            document.getElementById("ExtraAddress").value = data.jibunAddress;
            document.getElementById("StreetAddress").value = roadAddr;

            // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
            if(roadAddr !== ''){
                document.getElementById("ExtraAddress").value = extraRoadAddr;
            } else {
                document.getElementById("ExtraAddress").value = '';
            }
        }
    }).open();
}

// 아이디 중복 체크
function double_Check(){
    var url = '/Sign/double_Check';
    var method = 'POST';

    var userAccount = new Object();

    userAccount.ID = document.getElementById('ID').value;

    data_Action(url, method, userAccount);
}

// Sign_Up 메소드
function Sign_Up(){
    var url = '/Sign/Sign_Up.do';
    var method = 'POST';

    var userAccount = new Object();

    userAccount.ID = document.getElementById('ID').value;
    userAccount.PW = document.getElementById('PW1').value;
    userAccount.PW2 = document.getElementById('PW2').value;
    userAccount.NAME = document.getElementById('NAME').value;
    userAccount.ZIPCODE = document.getElementById('ZIPCODE').value;
    userAccount.StreetAddress = document.getElementById('StreetAddress').value;
    userAccount.DetailAddress = document.getElementById('DetailAddress').value;
    userAccount.Tel = document.getElementById('Tel').value;

    if (userAccount.ID == '') {
        document.getElementById('ID').focus();
        return;
    } else if (userAccount.PW == '') {
        document.getElementById('PW1').focus();
        return;
    } else if (userAccount.PW2 == '') {
        document.getElementById('PW2').focus();
        return;
    } else if (userAccount.NAME == '') {
        document.getElementById('PW1').focus();
        return;
    } else if (userAccount.ZIPCODE == '') {
        document.getElementById('SearchZIPCODE').focus();
        return;
    } else if (userAccount.StreetAddress == '') {
        document.getElementById('SearchZIPCODE').focus();
        return;
    } else if (userAccount.Tel == '') {
        document.getElementById('Tel').focus();
        return false;
    }

    data_Action(url, method, userAccount);
}

// 패스워드 확인
function password_Check(){
    // pw정규식 A-Z,a-z,0-9까지 8~16자리
    var regExp = /^[a-z0-9A-Z~!@#$%^&*()_+|<>?:{}]{8,16}$/;
    var PW1 = document.getElementById('PW1').value;
    var PW2 = document.getElementById('PW2').value;

    if (PW1 == PW2){
        document.getElementById('PW2').className = "form-control is-valid";
    } else {
        document.getElementById('PW2').className = "form-control is-invalid";
    }
    if (PW2 == ''){
        Rule_Check(PW2, regExp, 'PW2');
    }
}

// 전화번호 정리 -(하이픈)추가
function Tel_Check(){
    var Tel = document.getElementById('Tel').value;

    document.getElementById('Tel').value = Tel_Sort(Tel);
}

// ID 룰 체크
function id_Check(){
    // id정규식 A-Z,a-z,0-9까지 8~16자리
    var regExp = /^[a-z0-9A-Z_]{8,16}$/;

    var ID = document.getElementById('ID').value;

    Rule_Check_True_Rule(ID, regExp, 'ID');
}

// PW 룰 체크
function pw_Check(){
    // pw정규식 A-Z,a-z,0-9까지 8~16자리
    var regExp = /^[a-z0-9A-Z~!@#$%^&*()_+|<>?:{}]{8,16}$/;
    var PW1 = document.getElementById('PW1').value;

    Rule_Check_True_Rule(PW1, regExp, 'PW1');
}

//NAME 룰 체크
function name_Check(){
    // pw정규식 A-Z,a-z,0-9까지 8~16자리
    var regExp = /^[a-zA-Zㄱ-힣]{1,20}$/;
    var NAME = document.getElementById('NAME').value;

    Rule_Check_True_Rule(NAME, regExp, 'NAME');
}

//ADDRESS 룰 체크
function address_Check(){
    // pw정규식 A-Z,a-z,0-9까지 8~16자리
    var regExp = /^[a-zA-Zㄱ-힣,]$/;
    var DetailAddress = document.getElementById('DetailAddress').value;

    Rule_Check_False_Rule(DetailAddress, regExp, 'DetailAddress');
}