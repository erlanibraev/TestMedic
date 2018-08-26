$ ->
  $.get "/rest/persons", (persons) ->
    $.each persons, (index, person) ->
      $("#persons").append $("<li>").text person.iin + ": " + person.lastName + " " + person.firstName + " " + person.middleName

