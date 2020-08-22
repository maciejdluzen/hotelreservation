let RoomTypesUtils = {

    getAllRoomsImages : function(id, elementId) {
        const url2 = '/auth/admin/images/' + id;

        fetch(url2)
            .then(function(response) {
                return response.blob();
            })
            .then(function(responseAsBlob) {
                document.getElementById(elementId).src = URL.createObjectURL(responseAsBlob);
            });
    },

    getAllRoomTypesWithoutImage : function() {
        const url = '/auth/admin/roomtypes/getall';

        let roomName = '#roomName';
        let description = '#description';
        let feature1 = '#feature1';
        let feature2 = '#feature2';
        let feature3 = '#feature3';
        let feature4 = '#feature4';
        let noGuests = '#noGuests';
        let rateNet = '#rateNet';
        let tax = '#tax';
        let rateGross = '#rateGross';
        let imageId = '#imageId'

        fetch(url)
        .then(function(response) {
            return response.json();
        })
        .then(function(responseAsJson) {
            for(let i in responseAsJson) {
                if(i === 3) {break; }
                $(roomName + i).html(responseAsJson[i].name)
                $(description + i).html(responseAsJson[i].description)
                $(feature1 + i).html(responseAsJson[i].feature1)
                $(feature2 + i).html(responseAsJson[i].feature2)
                $(feature3 + i).html(responseAsJson[i].feature3)
                $(feature4 + i).html(responseAsJson[i].feature4)
                $(noGuests + i).html('Liczba gości: ' + responseAsJson[i].noPersons)
                $(rateNet + i).html('Cena netto: ' + responseAsJson[i].rateNet + ' PLN')
                let vatFormatted = (responseAsJson[i].tax)*100 + '%';
                $(tax + i).html('VAT: ' + vatFormatted)
                $(rateGross + i).html('Cena brutto: ' + responseAsJson[i].rateGross + ' PLN')
                let imgId = responseAsJson[i].imageId; // image id from the response
                let elId = 'image' + i; // search for the element id to be passed to the function
                RoomTypesUtils.getAllRoomsImages(imgId, elId);
            }
        });
    }
};

RoomTypesUtils.getAllRoomTypesWithoutImage();
