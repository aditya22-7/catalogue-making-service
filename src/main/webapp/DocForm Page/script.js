document.addEventListener("DOMContentLoaded", (event) => {
	
	document.querySelector(".sku-pdf-btn2").addEventListener("click", function() {
		
		console.log("Generate clicked");
		
		document.querySelector(".output-space").innerHTML = "Loading....";
		
		let numtext = document.querySelector(".num-space");
				
		let jsonString1 = convertToArray(numtext.value);
		
		$.ajax({
	        url : '../LocalImgDownloadServlet',
			type:'GET',
			data: {skuNumArrayString: jsonString1},
			success : function(responseText) 
			        {
			            $('.output-space').html(responseText);
			        }
	    });
	  const originalText = this.innerHTML;
	  this.disabled = true;
	  this.innerHTML = "Wait..";

	  setTimeout(() => {
	    this.disabled = false;
	    this.innerHTML = originalText;
	  }, 2000);
	  
	});
	
	
	
	
	document.querySelector(".sku-pdf-btn1").addEventListener("click", function() {
						
		document.querySelector(".output-space").innerHTML = "Loading....";
			
			$.ajax({
		        url : '../AwsImgDownloadServlet',
				type:'GET',
				success : function(responseText) 
				        {
				            $('.output-space').html(responseText);
				        }
		    });
		  const originalText = this.innerHTML;
		  this.disabled = true;
		  this.innerHTML = "Wait..";

		  setTimeout(() => {
		    this.disabled = false;
		    this.innerHTML = originalText;
		  }, 110000);
		  
		});
	

	document.querySelector(".sku-excel-btn2").addEventListener("click", function() {
						
		document.querySelector(".output-space").innerHTML = "Loading...."; 
		console.log("Generate Btn Clicked !!!!");
		
		let numtext = document.querySelector(".num-space");
		
		let jsonString1 = convertToArray(numtext.value);
		
		if(jsonString1!=null){
			$.ajax({
					        url : '../SkuExcelServlet',	
							type:'GET',
							data: {sendInfoString : jsonString1},
							success : function(responseText) 
							        {
							            $('.output-space').html(responseText);
							        }
					    });
		}
		const originalText = this.innerHTML;
		  this.disabled = true;
		  this.innerHTML = "Wait..";
	
		  setTimeout(() => {
		    this.disabled = false;
		    this.innerHTML = originalText;
		  }, 2000);
			
	})

});

function convertToArray(numString){

	var arr = new Array();
	// This will return an array with strings "1", "2", etc.
	arr = numString.split(",");
	let numarr = arr.map(Number);
	
	let jsonobj = {skuNumberArray : numarr};
	let jsonString = JSON.stringify(jsonobj);
	
	return jsonString;
}
// 165,298,173,363,340












