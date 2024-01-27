package di

fun appModule() =
    listOf(
        ktorModule,
        platformModule,
        repositoryModule,
        useCaseModule,
        viewModelModule,
    )
