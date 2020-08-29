let DetailsUtils = {

    showAndHideFormFields : function () {

        if($('#selectedRoomType').val() === 'Twin Room') {
            $('#guestThreeField').hide();
            $('#guestFourField').hide();
        } else if ($('#selectedRoomType').val() === 'Double Room') {
            $('#guestFourField').hide();
        }
    },

    submitReservationForm : function () {
        document.forms[0].submit();
        document.forms[1].submit();
        document.forms[2].submit();
        document.forms[3].submit();
    }
}

DetailsUtils.showAndHideFormFields();