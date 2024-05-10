function downloadPdf() {
    fetch('/download/pdf')
        .then(response => response.blob())
        .then(blob => {
            // 创建一个临时 URL
            const url = window.URL.createObjectURL(blob);
            
            // 创建一个下载链接
            const a = document.createElement('a');
            a.href = url;
            a.download = '社員ID-2(002)給料明細.pdf';
            document.body.appendChild(a);
            a.click();

            // 释放临时 URL
            window.URL.revokeObjectURL(url);
        })
        .catch(error => {
            console.error('Error downloading PDF file:', error);
        });
}