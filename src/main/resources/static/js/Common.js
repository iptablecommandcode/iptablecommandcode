/*
Auth : 박치원
title : 전화번호 양식 정렬
Desc :  000-0000-0000 전화번호를 (3)-(4)-(4) 자리에 숫자 그룹을 지어 묶어준다.
*/
function PhoneNumberSetting(Number){
    return Number.replace(/^(\d{3})(\d{4})(\d{4})$/, `$1-$2-$3`);
}