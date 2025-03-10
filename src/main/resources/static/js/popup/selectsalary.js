//■工资明细MODEL画面Js
$("#searchEmployees").click(function() {
	var url = location.href + '/popupmodal';
	$.ajax({
		type: "POST",
		url: url,
		//        data: JSON.stringify(search),
		data: {name:$("#param_employeename").val()},
		dataType: 'json',
		cache: false,
		timeout: 600000,
        success: function (data) {
            var outputContent = "";
            
            outputContent='<table class="providerTable" th:if="${form != null}">'+
						  '<tr class="firstTr">'+
						  '	<th style="width:10%;text-align: center;">社員ID</th>'+
						  '	<th style="width:10%;text-align: center;">社員名</th>'+
						  '	<th style="width:10%;text-align: center;">給料年月</th>'+
						  '	<th style="width:10%;text-align: center;">基本給料</th>'+
						  '	<th style="width:10%;text-align: center;">勤務時間</th>'+
						  '	<th style="width:10%;text-align: center;">残業控除</th>'+
						  '	<th style="width:10%;text-align: center;">費用</th>'+
						  '	<th style="width:10%;text-align: center;">実給(税込)</th>'+
						  '	<th style="width:10%;text-align: center;">アクション</th>'+
						  '</tr>'
							
            $.each(data, function (index, item) {
                outputContent += '<tr><td><span>' + item.employeeId + '</span></td>'
                                    +'<td style="text-align: left;"><span>' + item.employeeNm    +      '</span></td>'
                                    +'<td style="text-align: left;"><span>' + item.salaryMonth +      '</span></td>'
                                    +'<td style="text-align: left;"><span>' + item.salary +      '</span></td>'
                                    +'<td style="text-align: left;"><span>' + item.workhours +      '</span></td>'
                                    +'<td style="text-align: left;"><span>' + item.disabledGeneration +      '</span></td>'
                                    +'<td style="text-align: left;"><span>' + item.cost +      '</span></td>'
                                    +'<td style="text-align: left;"><span>' + item.actualSalary +      '</span></td>'
                                    +'<td><button type="button" id="selectedButton" value="'+item.employeeId+"_"+item.salaryMonth+'" onclick="clickSelect(this);" class="btn btn-secondary" style="padding:1px;">選択</button></td></tr>';
            });
            
            outputContent += "</table>"
            
            $('#outputdiv').html(outputContent);
        },
        error: function (data) {
            console.log(data);
        }
	});

});


function clickSelect(obj) {
	
	//社員ID＋給料年月
	var selectedVal = obj.getAttribute("value");
	var valArrary = selectedVal.split("_");
	
	//分割後の配列を一覧画面の社員IDに設定する
	$("#employeeId").val(valArrary[0]);
	//分割後の配列を一覧画面の給料年月に設定する
	$("#salaryMonth").val(valArrary[1]);
	
	$("#closeButton").trigger("click");
    $('#outputdiv').html("");
	
}

$("#batuButton").click(function() {
	//社員選択画面の一覧をクリアする
    $('#outputdiv').html("");
});

$("#closeButton").click(function() {
	//社員選択画面の一覧をクリアする
    $('#outputdiv').html("");
});

$("#openSelectEmployeeScreen").click(function() {
	//費用一覧画面の社員名を社員選択画面へ渡す
    $('#param_employeename').val($('#employeeNm').val());
});

