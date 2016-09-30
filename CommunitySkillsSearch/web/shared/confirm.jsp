<%-- 
    Document   : confirm
    Created on : Sep 30, 2016, 2:59:02 PM
    Author     : andyc
--%>

<form action="${request_action}&id=${request_id}" method="post">
    <input type="submit" class="btn btn-primary" value="Confirm">
    <button type="button" onclick="goBack()" class="btn btn-primaryk">Go Back</button>
</form>
<script>
    function goBack() {
        window.history.back();
    }
</script>