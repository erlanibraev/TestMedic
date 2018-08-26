$ ->
  $.get "/rest/organizations", (organizations) ->
    $.each organizations, (index, organization) ->
      $("#organizations").append $("<li>").text(organization.name)
      $("#organization_id").append $("<option>").text(organization.name).attr("value", organization.id)



