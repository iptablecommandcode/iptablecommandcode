document.addEventListener("DOMContentLoaded", function () {

    var editorEl = document.querySelector('#editor');
    if (!editorEl) return;

    // 기존 hiddenContent textarea 에 있는 초기값 (수정 페이지용)
    var hiddenTextarea = document.getElementById('hiddenContent');
    var initialHTML = hiddenTextarea ? hiddenTextarea.value || hiddenTextarea.textContent || '' : '';

    // Quill 초기화
    var quill = new Quill('#editor', {
        theme: 'snow',
        placeholder: '내용을 입력하세요...',
        modules: {
            toolbar: [
                [{ 'header': [1, 2, 3, false] }],
                ['bold', 'italic', 'underline', 'strike'],
                [{ 'color': [] }, { 'background': [] }],
                [{ 'list': 'ordered' }, { 'list': 'bullet' }],
                [{ 'indent': '-1' }, { 'indent': '+1' }],
                [{ 'align': [] }],
                ['blockquote', 'code-block'],
                ['link'],
                ['clean']
            ]
        }
    });

    // 수정 페이지: 기존 내용 로딩
    if (initialHTML) {
        quill.root.innerHTML = initialHTML;
    }

    // 글쓰기 폼 submit 처리
    var boardForm = document.getElementById('boardForm');
    if (boardForm) {
        boardForm.addEventListener('submit', function () {
            hiddenTextarea.value = quill.root.innerHTML;
        });
    }

    // 수정 폼 submit 처리
    var editForm = document.getElementById('editForm');
    if (editForm) {
        editForm.addEventListener('submit', function () {
            hiddenTextarea.value = quill.root.innerHTML;
        });
    }
});
