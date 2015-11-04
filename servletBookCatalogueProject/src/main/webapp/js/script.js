$(document).ready(function(){
  // Removing books with AJAX
  $(".remove_book_button").click(function(e){
    // add new variable with button - it is important because THIS will contain another things in contexts of different functions below
    var button = $(this);
    // Don't let browser to go to another page
    e.preventDefault();
    // Send AJAX request instead
    $.get(button.attr("href"), {}, function(response){
      // No notices?
      if(response.notices.length == 0){
        // Remove this book block with animation
        button.closest(".book").slideUp(1000, function(){
          // And remove it from DOM
          $(this).remove();
        });
      }
      else{
        alert(response.notices.join(","));
      }
    }, "json");
  });
});