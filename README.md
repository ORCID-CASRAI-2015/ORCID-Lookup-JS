# ORCID-Lookup-JS -- A JavaScript widget for ORCID lookup

## Use Cases

> Example story: As an author I am asked to enter my co-author's ORCID iD when I submit our conference paper, I want this to be accurate and painless

> Example story: As an adminstrative assistant I have to upload information for Professor X and the form asks for her ORCID iD, I want to find this easily and quickly

Simple web forms that want an ORCID iD often end up presenting users with a text box to type in the numeric ORCID iD directly (a slightly embarrassing example on the ORCID site is the [public data file form](https://orcid.org/content/orcid-public-data-file-2014). To add insult to injury, such forms often then ask for name and affiliation which is probably available from the ORCID record! 

There are uses for lookup for the ORCID iD of the user doing the lookup that are not covered here. This particular use case focuses on the case where the identity (ORCID iD) of the user is known and they are looking for the ORCID iD of a related person.

It would be nice to have a JavaScript widget that could be easily referenced by someone with limited programming skills building an HTML form. The widget should not only do auto-complete and lookup based on ORCID APIs but also provide ways to pre-fill other form fields (such as name and affiliation) based on the public ORCID profile data.

## Issues with finding another person's ORCID iD

The main concern with any system for lookup of another person's ORCID (by a co-author, colleague, or perhaps support staff) is that it will be error-prone. Errors might occur because of user laziness ("oh, I'll just pick the first John Smith..."), or because the user doesn't think to check ("oh, I found Isabelle Frank", but then it turns out to be a different person than the one intended).

One strategy is to display additional information in the lookup results. For example, showing the affiliation with the name might allow me to recognize that I'm looking for "John Smith (Cornell)" and not "John Smith (Barcelona)". There might still be problems with multiple John Smith's at one institution.

The strategy that we are most familiar with, from Facebook and other social networking sites, is use of the connectedness graph to find matches. Perhaps the tightest sense of community within the ORCID data is the co-author network. One could attempt to find matching by pivoting of work ids (DOIs) to find co-authors as a pool of possible matches. (This could be extended to 2-degrees of separation but that would likely be prohibitive without specific API support.) Another possible pivot would be institutional affiliations.

## Related work

### ANDS JS lookup widget

The Australian National Data Service (ANDS) developed a [JS lookup widget](http://developers.ands.org.au/widgets/orcid_widget/). This offers two modes of operation:

  1. ORCID entry and verification - if the user already know the ORCID, it may be entered into the text field and then when "Lookup" is clicked, the widget makes a request to the ORCID API and displays either a "Not Found" message, or displays public information from the ORCID record.
  2. ORCID search by name - if the user doesn't know the ORCID, they may click on "Search" and then enter a name in a second search box to search for an ORCID profile that matches. If one of the names returned in the result is clicked on then the ORCID iD is auto-filled into the iD box and the same information as in mode 1 is displayed.

This is an excellent starting point but here are some thoughts about possible improvement and extension:

  * The switch between two modes of operation is a little awkward. Using just one text entry box would be cleaner. It could show name and ORCID iD when completed and allow entry of either when the other is not known.
  * It would be good to have this work like an auto-complete on name and ORCID iD rather than focused on ORCID iD via search.
  * When displaying the search results based on name it would be good to display some additional information (when available) to help distinguish the results. For example, if one searches for "john smith" then at present there are 10 results show, all just the string "john smith" (various capitalizations) except for one that has a middle name.
  * It would be great to have some way to have the results populate other fields on the page, for example to pre-fill first and last names, affiliation, ORCID etc..
