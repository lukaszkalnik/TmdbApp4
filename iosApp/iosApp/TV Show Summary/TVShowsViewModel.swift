import Foundation
import shared

class TVShowsViewModel: ObservableObject {
    @Published var tvShows = [TVShow]()
    
    private var tmdbApi = koin.getTmdbApi()
    
    func fetchShows() {
        tmdbApi.getPopularTVShows { [weak self] tvShowsPage, error in
            if let tvShowsPage = tvShowsPage {
                self?.tvShows = tvShowsPage.results
            }
            if let error = error {
                print(error.localizedDescription)
            }
        }
    }
}
