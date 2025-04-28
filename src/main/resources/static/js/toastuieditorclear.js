document.addEventListener("DOMContentLoaded", function () {
    const editor = new toastui.Editor({
        el: document.querySelector('#editor'),
        height: '500px',
        initialEditType: 'wysiwyg',
        previewStyle: 'vertical',
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
