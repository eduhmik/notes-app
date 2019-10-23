import android.app.Application

class CoreApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CoreApplication)
            modules(listOf(dbModule, repositoryModule, uiModulels))
        }
    }
}