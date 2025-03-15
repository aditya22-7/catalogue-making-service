<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Form</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script
        src="https://code.jquery.com/jquery-3.7.1.js"
        integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
        crossorigin="anonymous">    
    </script>
    <script src="https://kit.fontawesome.com/3516857208.js" crossorigin="anonymous"></script>
    
    
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <form class="myForm-wo-new">
        <div class="main-class">
            <div class="sku-pdf">
                <div class="sku-pdf-content">
                    <label style="font-size: 30px;" class="sku-pdf-label">SkuDetails PDF</label>
                    <div class="field1">
                        <label style="font-size: 23px;">Download from AWS</label>
                        <button class="sku-pdf-btn1" type="button" style="margin-left : 15px;">Download</button>
                    </div>
                    <div class="field2"> 
                        <label style="font-size: 23px;">Generate PDF</label>
                        <button class="sku-pdf-btn2" type="button" style="margin-left : 86px;">Generate</button>
                    </div>
                </div>
            </div>
            <div class="sku-excel">
                <label class="sku-excel-label" style="font-size: 30px;">Sku Excel Sheet</label>
                <div class="sku-excel-btn">
                    <label style="font-size: 23px;">Generate Excel</label>
                    <button class="sku-excel-btn2" type="button">Generate</button>
                </div>
            </div>
        </div>
        <div class="output-div">
        	<!--<input class="output-space" type="text">-->
        	<textarea class="output-space" placeholder="Output message" rows="20" cols="70"></textarea>
        	<textarea class="num-space" placeholder="Enter Comma Separated values" rows="20" cols="70"></textarea>
        </div>
    </form>
    <script>
    	console.log("Hello World from this script !!!");
    </script>
    <script src="script.js" ></script>
</body>
</html>