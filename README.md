# DESCRIPTION

The app has 2 distinct page.

The first page will have a title of the app and a list of buttons labelled by the category/genre of
books available.

Clicking on the buttons should transition the user to the next page, which would display the
books matching the selected category. If the user enters any text in the
search bar, the API MUST be invoked again to search for all the books whose title or
author match the given input text, while maintaining the currently selected category/genre
filter.

The API response contains links for books in different formats. If the user taps on any book the
app must open the web browser pointing to the book in one of the following formats, the format
higher in the list must be preferred, falling back to the next available format (if given format is
not available)

● HTML

● PDF

● TXT

If none of the above formats are available, the app must display an error
message.
