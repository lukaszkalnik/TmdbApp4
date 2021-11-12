import SwiftUI
import shared

struct ContentView: View {
    
    @ObservedObject
    var observableTmdbApiModel = ObservableTmdbApiModel()

	var body: some View {
        Text(observableTmdbApiModel.tvShows ?? "No data")
            .onAppear(perform: {
                observableTmdbApiModel.activate()
            })
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}

class ObservableTmdbApiModel: ObservableObject {
    
    private var tmdbApi = koin.getTmdbApi()
    
    @Published
    var tvShows: String?
    
    func activate() {
        tmdbApi.getPopularTVShows { tvShowsPage, error in
            if let tvShowsPage = tvShowsPage {
                self.tvShows = tvShowsPage.description()
            }
            if let error = error {
                self.tvShows = error.localizedDescription
            }
        }
    }
}
