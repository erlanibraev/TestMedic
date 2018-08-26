$ ->
  $.get "/rest/persons", (persons) ->
    $.each persons, (index, person) ->
      $("#persons").append $("<li>").text(person.iin + ": " + person.lastName + " " + person.firstName + " " + person.middleName)
      $("#person_id").append $("<option>").text(person.iin + ": " + person.lastName + " " + person.firstName + " " + person.middleName).attr("value", person.id)


