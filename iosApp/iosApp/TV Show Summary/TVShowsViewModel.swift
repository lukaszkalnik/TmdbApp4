import Foundation
import shared

class TVShowsViewModel: ObservableObject {
    @Published var uiState: TVShowsState?
    
    private var sharedViewModel: TVShowsSharedViewModel = koin.getTVShowsSharedViewModel()
    private var uiStateWatcher: Closeable?
    
    init() {
        uiStateWatcher = SharedViewModelsKt.watchUiState(viewModel: sharedViewModel).watch { [weak self] tvShowsState in
            self?.uiState = tvShowsState
        }
    }
    
    deinit {
        uiStateWatcher?.close()
    }
}
