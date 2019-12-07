/*------------------------------------------------------
This is the JS library file for timeOffRequestAddEdit.jsp page
-------------------------------------------------------*/
/*
   The purpose of this function is to edit data on the
   time off request add and edit page
   Author:        Julie Cortez
   Date Created:  12/06/2019
*/


/*
   The purpose of this function is to clear any styles applied for validation errors
*/
function clearForm() {
    // reset background color on fields being edited
    document.editTimeOffRequestForm.userName.style = "";
    document.editTimeOffRequestForm.startDate.style = "";
    document.editTimeOffRequestForm.endDate.style = "";
}  // end of clearForm function

