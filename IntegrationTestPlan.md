# TEMPLATE Integration Test Plan

## Instructions

*Fill out the test plan following the instructions provided, replacing/removing the text in italics (including this section!) as you go.*

## Purpose

This captures the test plan for the testing of endpoints through automated integration tests.

## Product Background

*Provide a quick summary of your product and what it does.*

### Use Cases to be tested

*List the use cases you will test here. These should be the same list from your
design doc (though might be updated based on what you've learned since
then!). You will refer back to these use cases in the test plans below.*

# Automated Integration Test Plan

*Organize your tests by use cases from assignment description. Provide the
entire "Use Case:..." section below for each Use Case you will implement
in the project. If you have more than one test case for a given use
case, repeat the "Test Case" section below for each test case in that
use case.*

*The goal should be that any member of your team could take this list of
integration tests and add these automated tests to the integration test
package.*

## Use Case: *[New user signs up and posts one comment ]*

### **Test case name: *[newUser_withValidInputs_doesNotExceedThreshold]***

**Acceptance criteria:**

1. *The new user does not exceed the comment threshold within the probationary period, the account remains active.* 

**Endpoint(s) tested: /comment**

1. *(List only the endpoints actually tested (the "WHEN" part of your
   test, which might have multiple steps in an integration test)) /comment*

**GIVEN (Preconditions): user was created less than 24 hours ago**

1. *(List the conditions that must be true for the test case to take)* 

**WHEN (Action(s)):User account created less than 24 hours ago has not exceeded comment threshold within probation period**

1. *(List the steps that we're actually testing to verify that they work
   correctly. Often only one, but some integration tests might contain
   multiple WHEN steps for complex situations)*

**THEN (Verification steps):User account remains active**

1. *(List the steps to verify/assert that the expected behavior actually
   happens, include any relevant invariants here as well.)*

**Is there any clean-up needed for this test?** if user exceeds comment threshold deactivate account
 
1. *(Is there anything we need to do after this test finishes, to clean up and leave our service like we found it?)*
no

   
## Use Case: *User inputs the wrong username to access account*

### **Test case name: *[userInput_usernameIncorrect]***

**User can access account when providing correct username**

1. *(List what must be true to verify the use case has been implemented
   correctly)*

**Endpoint(s) tested:/user/{username}**

1. *(List only the endpoints actually tested (the "WHEN" part of your
   test, which might have multiple steps in an integration test))*

**GIVEN (Preconditions): A username**

1. *(List the conditions that must be true for the test case to take
   place.)*

**WHEN (Action(s)): Username not recognized**

1. *(List the steps that we're actually testing to verify that they work
   correctly. Often only one, but some integration tests might contain
   multiple WHEN steps for complex situations)*

**THEN (Verification steps): verify a invalidUsernameException was thrown**

1. *(List the steps to verify/assert that the expected behavior actually
   happens, include any relevant invariants here as well.)*

**Is there any clean-up needed for this test?**
 no
1. *(Is there anything we need to do after this test finishes, to clean
   up and leave our service like we found it?)*
no