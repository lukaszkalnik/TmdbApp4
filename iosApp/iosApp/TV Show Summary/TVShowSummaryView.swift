import SwiftUI
import shared
import Kingfisher

struct TVShowSummaryView: View {
    @StateObject var viewModel = TVShowsViewModel()
    
    var body: some View {
        NavigationView {
            ZStack {
                Color("purple_700")
                    .ignoresSafeArea()
                VStack {
                    let tvShowsState: TVShowsState? = viewModel.uiState
                    if (tvShowsState as? TVShowsState.Loading) != nil {
                        ProgressView()
                            .progressViewStyle(CircularProgressViewStyle(tint: Color.green))
                            .scaleEffect(1.5, anchor: .center)
                    } else if let tvShowsAvailable = tvShowsState as? TVShowsState.TVShows {
                        ScrollView {
                            LazyVStack {
                                ForEach(tvShowsAvailable.tvShows, id: \.id) {
                                    ShowView(show: $0)
                                }
                            }
                            .listStyle(PlainListStyle())
                            .background(Color("CardViewBackground"))
                        }
                    }
                }
                .navigationTitle("Popular TV Shows")
                .navigationBarTitleDisplayMode(.inline)
            } 
        }
    }
}

private struct ShowView: View {
    let show: TVShow
    
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            Text(show.name)
                .font(.title2)
            KFImage(URL(string: show.backdropImageUrl ?? ""))
                .resizable()
                .aspectRatio(contentMode: .fit)
            Text(show.overview)
                .font(.body)
                .multilineTextAlignment(.leading)
            Text(show.originCountries.joined(separator: ", "))
                .font(.subheadline)
                .foregroundColor(.secondary)
        }
        .padding(.top)
        .padding(.horizontal)
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        TVShowSummaryView()
    }
}
