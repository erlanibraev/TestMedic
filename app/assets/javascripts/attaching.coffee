$ ->
  $.get "/rest/attachings", (attachings) ->
    $.each attachings, (index, attaching) ->
      $("#attachings").append $("<li>").text(attaching.status + " - " +  attaching.person.iin + ": " + attaching.person.lastName + " " + attaching.person.firstName + " " + attaching.person.middleName)
          .append($("<form>")
            .attr("method","POST")
            .attr("id", "attach"+index)
            .attr("action","/rest/attach")
            .append($("<input>")
                .attr("type","hidden")
                .attr("name","id")
                .attr("value", attaching.id))
            .append($("<button>").text("Прикрепить")))



