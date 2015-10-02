var map = null;
var marker = null;
var geocoder = null;

var loadListener;

$(document).ready(function() {
	
	if($.browser.msie){
		try{
			var test = new google.maps.Map(document.getElementById("dummy"));
		}catch(error){}
		
		try{
			new google.maps.Marker();
		}catch(error){}
		
		try{
			getGeocoder();
		}catch(error){}

		
		setTimeout("init()", 2000);
	}else{
	
	
		init();
	}
	
	
	
	
});

function init() {
	$('input[name$="model.town"]').change(function() {
		updatePos();
	});

	$('input[name$="model.street"]').change(function() {
		updatePos();
	});

	$('input[name$="model.longitude"]').change(
			function() {
				var latlng = new google.maps.LatLng($(
						'input[name$="model.latitude"]').val(), $(
						'input[name$="model.longitude"]').val());
				setMarker(latlng);
			});

	$('input[name$="model.latitude"]').change(
			function() {
				var latlng = new google.maps.LatLng($(
						'input[name$="model.latitude"]').val(), $(
						'input[name$="model.longitude"]').val());
				setMarker(latlng);
			});

	initMap();

}

function initMap() {

	var latlng = new google.maps.LatLng(mapStartPosLat, mapStartPosLon);

	var myOptions = {
		zoom : mapStartZoom,
		center : latlng,
		mapTypeId : google.maps.MapTypeId.ROADMAP
	};

	if (map == null) {
		map = new google.maps.Map(document.getElementById("mapCanvas"),
				myOptions);

	}

	var mapCentered = setMarkerAfterPosFields();

	map.centered = mapCentered;
	loadListener = google.maps.event.addListener(map, 'tilesloaded',
			function() {

				geocoder = new google.maps.Geocoder();

				if (!this.centered) {
					geocoder.geocode({
						'address' : countrycode
					}, function(results, status) {
						var bounds = new google.maps.LatLngBounds();
						bounds = results[0].geometry.viewport;
						map.fitBounds(bounds);
						map.setZoom(mapStartZoom);
					});
				}
				google.maps.event.removeListener(loadListener);

			});

}

function updatePos() {
	if ($('input[name$="town"]').val() != "") {
		var searchString = "";
		if ($('input[name$="street"]').val() != "") {
			searchString += $('input[name$="street"]').val() + ", ";
		}
		searchString += $('input[name$="town"]').val() + ", ";
		searchString += " " + countryname;
		geocoder.geocode({
			'address' : searchString
		}, function(results, status) {
			if (status == google.maps.GeocoderStatus.OK) {
				var ownPos = results[0];
				setMarker(ownPos.geometry.location);

				setPosFields(ownPos.geometry.location.lng(),
						ownPos.geometry.location.lat())

			} else {

			}
		});

	}

}

function setMarkerAfterPosFields() {
	if ($('input[name$="model.longitude"]').val() != ""
			&& $('input[name$="model.longitude"]').val() != "") {
		var latlng = new google.maps.LatLng($('input[name$="model.latitude"]')
				.val(), $('input[name$="model.longitude"]').val());

		setMarker(latlng);
		return true;
	}
	return false;
}

function setMarker(latlng) {
	if (marker != null) {
		marker.setPosition(latlng);
	} else {

		marker = new google.maps.Marker({
			map : map,
			position : latlng,
			draggable : true
		});

		google.maps.event.addListener(marker, 'dragend', function() {
			markerMoved();
		});

	}
	map.setCenter(latlng);
}

function setPosFields(lng, lat) {
	$('input[name$="model.longitude"]').val(roundNumber(lng, 6));
	$('input[name$="model.latitude"]').val(roundNumber(lat, 6));

}

function roundNumber(rnum, rlength) { // Arguments: number to round, number of
										// decimal places
	var newnumber = Math.round(rnum * Math.pow(10, rlength))
			/ Math.pow(10, rlength);
	return newnumber;
}

function markerMoved() {
	setPosFields(marker.getPosition().lng(), marker.getPosition().lat());

}

function getGeocoder() {
	if (geocoder == null) {
		try {
			geocoder = new google.maps.Geocoder();
		} catch (err) {
			// do nothing
		}
	}
	return geocoder;

}
