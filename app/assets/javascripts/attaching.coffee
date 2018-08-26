$ ->
  $.get "/rest/attachings", (attachings) ->
    $.each attachings, (index, attaching) ->
      $("#attachings").append $("<li>").text attaching.status + " - " +  attaching.person.iin + ": " + attaching.person.lastName + " " + attaching.person.firstName + " " + attaching.person.middleName

