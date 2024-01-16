package di

fun appModule() = listOf(
    dataStoreModule,
    ktorModule,
    repositoryModule,
    useCaseModule,
    viewModelModule
)