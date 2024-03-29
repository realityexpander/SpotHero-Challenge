# SH-3

## User Story

As a Product Manager, I would like to know the level of effort required to complete the ticket [SH-4](/docs/ticket-sh4).

## Acceptance Criteria

* An estimate (in hours) for the work.
* A brief overview of how you would implement the solution at a technical level.
* Any questions that the Product Manager should take back to the stakeholders.
* Any additional concerns or questions you have for the Product Manager or Product Designer.


## Estimate
* 3-9 hours

## Technical Level
* Basic to Intermediate
  * I would use compose for the UI Components and layouts. There are some 
    built-in components that work well for the text entry fields with hints.
  * For the gradient, there is also a gradient component that I would use.
  * This low-end estimate is solely for the UI, and not the backend or validation.
* Why Intermediate?
  * Validation code (if required) will take extra time to implement.
  * Customization of the Text Input field to pixel-match the design is a 
    increased effort.
  * Upper end estimate is for the customization and validation and very much
    depends on the needs of the product manager & designer.

## Questions for the Product Manager
* Level of validation for the user input.
  * Credit card libraries available? Live validation?
  * Email validation? Live email validation?
  * Zip code validation?
  * Does UI Testing code need to be written? To what extent?
  * Is the navigation code already written?

## Questions for the Product Designer
* Colors for the gradient used at the top.
* The UI at the top is non-standard for Android applications.
  * How important is this? This can be made to look like the iOS version, 
      but it will require rendering our own version of the clock, wifi signal,
      signal strength and battery icons. This is a non-trivial task.
  * Can we just have the gradient where the "Checkout" banner is? And leave the
    rest of the top bar to look like standard Android applications? 
  * Are the Icons from a standard library, and will they be supplied?
  * Is the font currently installed in the app?
  * Android Compose has a standard text input Field. How close does to the image provided does the UI
    have to look for the text input Fields? Can we use the Android standard textField? 
    * Customization of this textField will take extra time and require more testing.