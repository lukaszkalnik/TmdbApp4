import SwiftUI

struct TVShowSummaryView: View {
    @StateObject var showsModel = TVShowsViewModel()
    
    var body: some View {
        NavigationView {
            VStack {
                if showsModel.tvShows.isEmpty {
                    Text("No data")
                } else {
                    LazyVStack {
                        ForEach(showsModel.tvShows, id: \.id) {
                            Text($0.name)
                        }
                    }
                }
            }
            .navigationTitle("Popular TV Shows")
        }
        .onAppear {
            showsModel.fetchShows()
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        TVShowSummaryView()
    }
}
