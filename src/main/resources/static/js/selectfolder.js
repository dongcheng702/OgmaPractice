var selectedFolderPath = ""; // 保存选择的文件夹路径

function selectFolder() {
    // 触发文件夹选择器
    document.getElementById('folderInput').click();

    // 当文件夹选择器的值发生变化时执行的回调
    document.getElementById('folderInput').addEventListener('change', function () {
        selectedFolderPath = document.getElementById('folderInput').files[0].webkitRelativePath.split('/')[0];
     	// 更新storeId输入框的值
        document.querySelector("input[name='storeId']").value = selectedFolderPath;
    });
}