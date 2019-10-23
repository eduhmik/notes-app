import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dbModule = module {
    single { NoteDatabase.getInstance(
        context = get()
    )}
    factory { get<NoteDatabase>().noteDao() }
}

val repositoryModule = module {
    single { NoteRepository(get())}
}

val uiModule = module {
    factory { NoteAdapater() }
    viewModel { NoteViewModel(get()) }
}