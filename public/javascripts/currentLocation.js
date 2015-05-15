if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(function(position){
        var latitude = position.coords.latitude;
        var longitude = position.coords.longitude;
    });


    var map = L.map('map')

    L.tileLayer('https://{s}.tiles.mapbox.com/v3/{id}/{z}/{x}/{y}.png', {
        maxZoom: 18,
        attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, ' +
        '<a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
        'Imagery © <a href="http://mapbox.com">Mapbox</a>',
        id: 'examples.map-i875mjb7'
    }).addTo(map);

    map.locate({setView: true, maxZoom: 16});

    function onLocationFound(e) {
        var radius = e.accuracy / 2;

        L.marker(e.latlng).addTo(map)
            .bindPopup("You are within " + radius + " meters from this point").openPopup();
      //  L.circle(e.latlng, radius).addTo(map).bindPopup("I'm here!");

    }

    var popup = L.popup();

    function onMapClick(e) {
        var clickCoords = e.latlng.toString();
        popup
            .setLatLng(e.latlng)
            .setContent("You clicked the map at " + clickCoords)
            .openOn(map);

        var VRegExp = new RegExp(/-?\d.[\d,.][^,()]*/);
        var VResultLat = VRegExp.exec(clickCoords);
        document.latlng.lat.value = VResultLat;

        VRegExp = new RegExp(/ -?\d.*\d/);
        var VResultLng = VRegExp.exec(clickCoords);
        document.latlng.lng.value = VResultLng;

        //console.log(clickCoords);
    }

    map.on('click', onMapClick);

    map.on('locationfound', onLocationFound);

}else {
    alert("Geolocation API is not supported in your browser. :(");
}
