/**
 * param : _this 单选组件 radio
 * param : _value 需要比较的值
 * 如果单选按钮的值与_value相同 选中该单选按钮
 */
function radioIsChecked(_this,_value){
	
	if(_this.value == _value || _this.id == _value){
		_this.checked = true;
	}
}


/**
 * 返回选择组件的值
 * @param _this 
 * @return
 */
function radioCheckedValue(_this){
	
	if(_this.checked == true){
		return _this.value;
	}
}


/**
 * 测试数值中是否包含参数值
 * @return
 */
function containsComBoxValue(_this,str){
	_array = str.split(",");
	return _array.contains(_this.value);
}

/**
 * 传进一个字符串。打成数组。
 * @return
 */
function radioChecked(_str){
	_array = _str.split(",");
	for(var i=0;i<_array.length;i++){
		if(_array[i] != ""){
			$(_array[i]).checked = true;
		}
	}
}


function radioGroupSetValue(_str){
	_array = _str.split(",");
	for(var i=0;i<_array.length;i++){
		if($(_array[i]).checked){
			return $(_array[i]);
		}
	}
	
}

function isChecked(_id) {
	return $(_id).checked;
}



//显示文本框
function showDisabled(_this,_showID){
	var _array = _showID.split(",");
	for(var i=0;i<_array.length;i++){
		if($(_array[i])){
			$(_array[i]).disabled = !_this.checked;
		}
	}
}

//显示文本框
function showTextDisabled(_showID){
	var _array = _showID.split(",");
	for(var i=0;i<_array.length;i++){
		if($(_array[i])){
			$(_array[i]).disabled = false;
		}
	}
}

/**
 * 数组去重复字符串 
 * @return
 */
Array.prototype.delRepeat=function(){
	 var newArray=[];
	 var provisionalTable = {};
	 for (var i = 0, item; (item= this[i]) != null; i++) {
	        if (!provisionalTable[item]) {
	            newArray.push(item);
	            provisionalTable[item] = true;
	        }
	    }
	    return newArray;
}



//调试对象的属性信息
function showAttributes(obj){
	 var   key; 
	 for(key   in   obj) 
	 {
		 alert(key + " == " + obj[key]);
	 } 
}
