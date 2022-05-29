Example project written in Kotlin to demonstrate use of Clean Architecture
The project doesn't use Dagger or other DI frameworks because of the small project size, and is written in a way that
minimizes the amount of calls to API with use of Shared View Model for List/Detail scenario.
For instance when we update user we call API and then update specific user in the list trough Shared View Model.
Replace YOUR_API_KEY in UsersApiService with your api key from https://gorest.co.in/
