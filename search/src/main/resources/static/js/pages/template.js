"use strict";

$(document).ready(function () {
	
	// ===== Functions ===================================================
	
	// Returns an object with the properties "button": <jQueryButton>, and "tabindex": as derived from the jQueryButton object. 
	function createButtonObject(jQueryButton) {
		return { button: jQueryButton, tabindex: jQueryButton.attr('tabindex') };
	}
	
	// ===== "Main" ===================================================
	
	var searchContent = $('#searchRow');
	var resultsContent = $('#ResultsRow');
	
	var executeButton = $("#executeButton");
	var executedButtonObject = createButtonObject(executeButton);
	
	// ===== "Handlers" ================================================
    var radioValue = $("input[name='action']:checked").val();

	if (radioValue == 1) {
		searchContent.removeClass("hidden");
		resultsContent.removeClass("hidden");
	}
	
	executeButton.on('click', function () {
		if (resultsContent.hasClass("hidden")) {
			resultsContent.removeClass("hidden");
		}
	});
	
	$("input[name='action']").change( function() {
        var radioValue = $("input[name='action']:checked").val();
        if (radioValue == 1) {
        	searchContent.removeClass("hidden");
        } else {
        	searchContent.addClass("hidden");
        	if (!resultsContent.hasClass("hidden")) {
    			resultsContent.addClass("hidden");
    		}
        }
    });
	
})