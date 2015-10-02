var map = null;
var listener = null;
$(document).ready(function() {
	init();
});

var retries = 0;

// set to true if fields or map is edited at the moment, so triggers won't fire
// and cause an infinite loop
var triggerMode = false;

function init() {

	// generation of example url
	$("#selectedCountries").click(function() {
		generateExampleUrl();
		generateHelperJsUrl();
		retries = 0;
		updateImageSets();
	});

	$("input:radio[name=model.type]").click(function() {
		generateExampleUrl();
		generateHelperJsUrl();
	});

	// updating map view after fields
	$('input[name$="model.startLatitude"]').change(function() {
		updateMapView();
	});
	$('input[name$="model.startLongitude"]').change(function() {
		updateMapView();
	});
	$('input[name$="model.startZoom"]').change(function() {
		updateMapZoom();
	});

	updateImageSets();

	// updating fields after map view

	if ($.browser.msie) {
		try {
			var test = new google.maps.Map(document.getElementById("dummy"));
		} catch (error) {
		}

		try {
			new google.maps.Marker();
		} catch (error) {
		}

		setTimeout("initMap()", 2000);
	} else {

		initMap();
	}

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

	setMapListener();

}

function roundNumber(rnum, rlength) { // Arguments: number to round, number of
	// decimal places
	var newnumber = Math.round(rnum * Math.pow(10, rlength))
			/ Math.pow(10, rlength);
	return newnumber;
}

function generateExampleUrl() {

	if ($('#selectedCountries option:selected').length > 0
			&& $('input:radio[name=model.type]:checked').val() != null) {
		$("#exampleUrlSpan").show();

		var url = "";
		if ($('input:radio[name=model.type]:checked').val() == "INTERNET") {
			url = urlInternetPattern;
		} else {
			url = urlIntranetPattern;
		}
		url = url.split("?")[0];

		var country = $("#selectedCountries").val()[0];
		url += "?app_id=" + $("#application_key").val();
		var langString = countries[country];
		url += "&lang=" + langString;
		$("#exampleUrlSpan").html(url);
	}

}

function generateHelperJsUrl() {
	if ($('#selectedCountries option:selected').length > 0
			&& $('input:radio[name=model.type]:checked').val() != null) {
		$("#helperJsUrlSpan").show();

		var url = "";
		if ($('input:radio[name=model.type]:checked').val() == "INTERNET") {
			url = urlInternetPattern;
		} else {
			url = urlIntranetPattern;
		}
		url = url.split("/index?")[0];

		url += "/js/iframe-helper.js";
		$("#helperJsUrlSpan").html(url);
	}
	
}

function updateMapView() {
	if (map != null && !triggerMode) {
		triggerMode = true;
		var curStartLatString = $('input[name$="model.startLatitude"]').val();
		var curStartLonString = $('input[name$="model.startLongitude"]').val();

		if (curStartLatString != "" && curStartLonString != "") {
			var curStartLat = parseInt(curStartLatString);
			var curStartLon = parseInt(curStartLonString);
			if (curStartLat != NaN && curStartLon != NaN && curStartLat > -180
					&& curStartLat < 180 && curStartLon > -180
					&& curStartLon < 180) {
				var latlng = new google.maps.LatLng(curStartLat, curStartLon);
				map.setCenter(latlng);
			}
		}
		triggerMode = false;
	}
}

function updateMapZoom() {

	if (map != null && !triggerMode) {
		triggerMode = true;
		var curStartZoomString = $('input[name$="model.startZoom"]').val();
		if (curStartZoomString != "") {
			var curStartZoom = parseInt(curStartZoomString);
			if (curStartZoom != NaN && curStartZoom > 0 && curStartZoom < 20) {
				map.setZoom(curStartZoom);
			}
		}
		triggerMode = false;
	}
}

function setMapListener() {
	listener = google.maps.event.addListener(map, 'bounds_changed', function() {
		if (!triggerMode) {
			triggerMode = true;
			$('input[name$="model.startZoom"]').val(map.getZoom());
			$('input[name$="model.startLatitude"]').val(
					roundNumber(map.getCenter().lat(), 6));
			$('input[name$="model.startLongitude"]').val(
					roundNumber(map.getCenter().lng(), 6));
			triggerMode = false;
		}
	});
}

function updateImageSets() {
	var countryCodes = $("#selectedCountries").val();
	if ($("input[name='selectedImageSet']:checked").val() != null) {
		selectedImageSetValue = $("input[name='selectedImageSet']:checked")
				.val();
	}
	var td = $("label[for='selectedImageSet']").parent().parent().children()[1];
	td.innerHTML = "";

	if (countryCodes != null && countryCodes.length != 0) {
		retries = 0;
		for ( var i = 0; i < imageSets.length; i++) {

			var emptyState = true;
			var completeState = true;
			for ( var j = 0; j < countryCodes.length; j++) {
				emptyState = emptyState && cisia[countryCodes[j]].pcEmpty
						&& cisia[countryCodes[j]][imageSets[i].id].ltEmpty;
				completeState = completeState
						&& cisia[countryCodes[j]][imageSets[i].id].pcComplete
						&& cisia[countryCodes[j]][imageSets[i].id].ltComplete;
			}

			if (!emptyState) {
				var checked = "";
				if (selectedImageSetValue == imageSets[i].id) {
					checked = "checked=\checked\"";
				}
				var completeText = "";
				if (!completeState) {
					completeText = " (" + notCompleteText + ")";
				}

				td.innerHTML += "<input type=\"radio\" value=\""
						+ imageSets[i].id
						+ "\" "
						+ checked
						+ " id=\selectedImageSet"
						+ imageSets[i].id
						+ " name=\"selectedImageSet\"><label class=\"radioLabel\" for=\"selectedImageSet"
						+ imageSets[i].id + "\">" + imageSets[i].name
						+ completeText + "</label><br/>";
			}
		}
	}else{
		//selective retry for ie + triggering later change
		if(retries < 5){
			retries++;
			setTimeout("triggerHelper()", 500);
		}
	}

}

function triggerHelper(){
	$('#selectedCountries').trigger('click');
}
