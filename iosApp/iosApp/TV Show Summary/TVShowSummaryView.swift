import SwiftUI
import shared

struct TVShowSummaryView: View {
    @StateObject var showsModel = TVShowsViewModel()
    
    var body: some View {
        NavigationView {
            VStack {
                if showsModel.tvShows.isEmpty {
                    Text("No data")
                } else {
                    List {
                        ForEach(showsModel.tvShows, id: \.id) {
                            ShowView(show: $0)
                        }
                    }
                    .listStyle(PlainListStyle())
                }
            }
            .navigationTitle("Popular TV Shows")
        }
        .onAppear {
            showsModel.fetchShows()
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
                    .font(.largeTitle)
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
