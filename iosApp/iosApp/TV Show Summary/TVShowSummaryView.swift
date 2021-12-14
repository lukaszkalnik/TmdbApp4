import SwiftUI
import shared

struct TVShowSummaryView: View {
    @StateObject var viewModel = TVShowsViewModel()
    
    var body: some View {
        NavigationView {
            VStack {
                let tvShowsState: TVShowsState? = viewModel.uiState
                if (tvShowsState as? TVShowsState.Loading) != nil {
                    ProgressView()
                        .progressViewStyle(CircularProgressViewStyle(tint: Color.green))
                        .scaleEffect(1.5, anchor: .center)
                } else if let tvShowsAvailable = tvShowsState as? TVShowsState.TVShows {
                    List {
                        ForEach(tvShowsAvailable.tvShows, id: \.id) {
                            ShowView(show: $0)
                        }
                    }
                    .listStyle(PlainListStyle())
                }
            }
            .navigationTitle("Popular TV Shows")
        }
    }
}

private struct ShowView: View {
    let show: TVShow
    
    var body: some View {
        ZStack {
            RoundedRectangle(cornerRadius: 16, style: .continuous)
                .fill(Color("CardViewBackground"))
            VStack(spacing: 8) {
                Text(show.name)
                    .font(.title)
                Text(show.overview)
                    .font(.body)
                    .multilineTextAlignment(.leading)
                Text(show.originCountries.joined(separator: ", "))
                    .font(.subheadline)
                    .foregroundColor(.secondary)
            }
            .padding(16)
            .multilineTextAlignment(.center)
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        TVShowSummaryView()
    }
}
