document.addEventListener("DOMContentLoaded", function () {
    // 기존 내용이 textarea에 있을 경우, 초기값으로 사용
    const initialContent = document.getElementById('hiddenContent').textContent || '';

    const editor = new toastui.Editor({
        el: document.querySelector('#editor'),
        height: '500px',
        initialEditType: 'wysiwyg',
        previewStyle: 'vertical',
        initialValue: initialContent,
        hooks: {
            addImageBlobHook: async (blob, callback) => {
                const formData = new FormData();
                formData.append('image', blob);
                const res = await fetch('/upload-image', {
                    method: 'POST',
                    body: formData
                });
                const data = await res.json();
                callback(data.url, 'image');
            }
        }
    });

    const form = document.getElementById('postForm');
    if (form) {
        form.addEventListener('submit', function () {
            document.getElementById('hiddenContent').value = editor.getHTML();
        });
    }
});
