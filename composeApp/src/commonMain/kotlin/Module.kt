import data.network.di.YouBikeApi
import data.repository.YouBikeApiRepositoryImpl
import domain.repository.YouBikeApiRepository
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MainViewModel()
    }
}

val youBikeApiModule = module {
    single<YouBikeApi> { YouBikeApi() }

    single<YouBikeApiRepository> {
        YouBikeApiRepositoryImpl(
            get()
        )
    }
}