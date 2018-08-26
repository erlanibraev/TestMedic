$ ->
  $.get "/rest/organizations", (organizations) ->
    $.each organizations, (index, organization) ->
      $("#organizations").append $("<li>").text organization.name

