// Load Songs
let songs = [];
let filteredSongs = [];
let currentSongIndex = 0;
let isPlaying = false;
let activeTab = 'playlist'; // playlist, favorites, recent
let shuffle = false;
let repeat = false;

// Player Variables
const audioPlayer = document.getElementById('audio-player');
const playBtn = document.getElementById('play-btn');
const prevBtn = document.getElementById('prev-btn');
const nextBtn = document.getElementById('next-btn');
const shuffleBtn = document.getElementById('shuffle-btn');
const repeatBtn = document.getElementById('repeat-btn');
const seekBar = document.getElementById('seek-bar');
const volumeBar = document.getElementById('volume-bar');
const volumeLabel = document.getElementById('volume-label');
const currentTimeLabel = document.getElementById('current-time');
const totalTimeLabel = document.getElementById('total-time');

const albumCover = document.getElementById('album-cover');
const songTitle = document.getElementById('song-title');
const songArtist = document.getElementById('song-artist');
const favoriteBtn = document.getElementById('favorite-btn');

const searchInput = document.getElementById('search-input');
const voiceSearchBtn = document.getElementById('voice-search-btn');
const voiceStatus = document.getElementById('voice-status');
const voiceModal = document.getElementById('voice-modal');
const closeVoiceBtn = document.getElementById('close-voice-btn');

const tabPlaylist = document.getElementById('tab-playlist');
const tabFavorites = document.getElementById('tab-favorites');
const tabRecent = document.getElementById('tab-recent');
const songListContainer = document.getElementById('song-list');

// Initialize App
fetch('data/songs.json')
  .then(response => response.json())
  .then(data => {
    songs = data;
    filteredSongs = [...songs];
    loadSong(0);
    renderList();
  })
  .catch(err => console.error('Error loading songs data:', err));

function loadSong(index) {
  if (songs.length === 0) return;
  currentSongIndex = index;
  const song = songs[currentSongIndex];
  
  audioPlayer.src = song.src;
  songTitle.textContent = song.title;
  songArtist.textContent = song.artist;
  albumCover.src = song.cover;
  
  // Reset seek bar
  seekBar.value = 0;
  currentTimeLabel.textContent = '0:00';
  totalTimeLabel.textContent = '0:00';
  
  // Set favorite state
  updateFavoriteUI(song.id);

  // Add to recently played
  addToRecent(song.id);

  // Highlight current item in list
  updateActiveSongInList();
}

// Play / Pause
function togglePlay() {
  if (isPlaying) {
    audioPlayer.pause();
    playBtn.textContent = '▶️';
    isPlaying = false;
  } else {
    audioPlayer.play()
      .then(() => {
        playBtn.textContent = '⏸️';
        isPlaying = true;
      })
      .catch(err => console.warn('Playback blocked or file missing:', err));
  }
}

playBtn.addEventListener('click', togglePlay);

// Next / Previous
function nextSong() {
  let index;

  if (shuffle) {
  do {
    index = Math.floor(Math.random() * songs.length);
  } while (index === currentSongIndex && songs.length > 1);
} else {
    index = currentSongIndex + 1;

    if (index >= songs.length) {
      index = 0;
    }
  }

  loadSong(index);

  if (isPlaying) {
    audioPlayer.play().catch(() => {});
  }
}

function prevSong() {
  let index = currentSongIndex - 1;
  if (index < 0) {
    index = songs.length - 1;
  }
  loadSong(index);
  if (isPlaying) {
    audioPlayer.play().catch(() => {});
  }
}

prevBtn.addEventListener('click', prevSong);
nextBtn.addEventListener('click', nextSong);
// Shuffle
shuffleBtn.addEventListener('click', () => {
  shuffle = !shuffle;
  shuffleBtn.classList.toggle('active', shuffle);
});
// Repeat
repeatBtn.addEventListener('click', () => {
  repeat = !repeat;
  repeatBtn.classList.toggle('active', repeat);
});

// Automatically play next song or repeat current song
audioPlayer.addEventListener('ended', () => {
  if (repeat) {
    audioPlayer.currentTime = 0;
    audioPlayer.play().catch(() => {});
  } else {
    nextSong();
  }
});

// Progress Bar
audioPlayer.addEventListener('timeupdate', () => {
  if (!audioPlayer.duration) return;
  const progressPercent = (audioPlayer.currentTime / audioPlayer.duration) * 100;
  seekBar.value = progressPercent;
  
  // Format times
  currentTimeLabel.textContent = formatTime(audioPlayer.currentTime);
  totalTimeLabel.textContent = formatTime(audioPlayer.duration);
});

seekBar.addEventListener('input', () => {
  if (!audioPlayer.duration) return;
  audioPlayer.currentTime = (seekBar.value / 100) * audioPlayer.duration;
});

function formatTime(seconds) {
  if (isNaN(seconds)) return '0:00';
  const mins = Math.floor(seconds / 60);
  const secs = Math.floor(seconds % 60);
  return `${mins}:${secs < 10 ? '0' : ''}${secs}`;
}

// Volume
volumeBar.addEventListener('input', () => {
  const volVal = volumeBar.value;
  audioPlayer.volume = volVal / 100;
  volumeLabel.textContent = `${volVal}%`;
});

// Playlist Rendering
function renderList() {
  songListContainer.innerHTML = '';
  let listToDisplay = [];

  if (activeTab === 'playlist') {
    listToDisplay = filteredSongs;
  } else if (activeTab === 'favorites') {
    const favorites = getFavorites();
    listToDisplay = songs.filter(s => favorites.includes(s.id));
  } else if (activeTab === 'recent') {
    const recentIds = getRecent();
    // Maintain order of recently played
    recentIds.forEach(id => {
      const song = songs.find(s => s.id === id);
      if (song) listToDisplay.push(song);
    });
  }

  if (listToDisplay.length === 0) {
    songListContainer.innerHTML = '<li style="text-align:center; padding: 20px; color:#a0a0c0;">No songs to display</li>';
    return;
  }

  listToDisplay.forEach(song => {
    const li = document.createElement('li');
    li.dataset.id = song.id;
    if (songs[currentSongIndex] && songs[currentSongIndex].id === song.id) {
      li.classList.add('active');
    }
    
    li.innerHTML = `
      <img class="list-cover" src="${song.cover}" alt="cover">
      <div class="list-info">
        <div class="list-title">${song.title}</div>
        <div class="list-artist">${song.artist}</div>
      </div>
    `;
    
    li.addEventListener('click', () => {
      const mainIndex = songs.findIndex(s => s.id === song.id);
      if (mainIndex !== -1) {
        loadSong(mainIndex);
        if (!isPlaying) {
          togglePlay();
        } else {
          audioPlayer.play().catch(() => {});
        }
      }
    });
    
    songListContainer.appendChild(li);
  });
}

function updateActiveSongInList() {
  const listItems = songListContainer.querySelectorAll('li');
  const currentSong = songs[currentSongIndex];
  if (!currentSong) return;
  
  listItems.forEach(li => {
    if (parseInt(li.dataset.id) === currentSong.id) {
      li.classList.add('active');
    } else {
      li.classList.remove('active');
    }
  });
}

// Tab Events
tabPlaylist.addEventListener('click', () => {
  switchTab('playlist', tabPlaylist);
});
tabFavorites.addEventListener('click', () => {
  switchTab('favorites', tabFavorites);
});
tabRecent.addEventListener('click', () => {
  switchTab('recent', tabRecent);
});

function switchTab(tabName, element) {
  activeTab = tabName;
  document.querySelectorAll('.tab-btn').forEach(btn => btn.classList.remove('active'));
  element.classList.add('active');
  renderList();
}

// Favorites
function getFavorites() {
  return JSON.parse(localStorage.getItem('assignment_favorites') || '[]');
}

function updateFavoriteUI(songId) {
  const favorites = getFavorites();
  if (favorites.includes(songId)) {
    favoriteBtn.classList.add('active');
    favoriteBtn.textContent = '❤️ Favorited';
  } else {
    favoriteBtn.classList.remove('active');
    favoriteBtn.textContent = '🤍 Favorite';
  }
}

favoriteBtn.addEventListener('click', () => {
  const currentSong = songs[currentSongIndex];
  if (!currentSong) return;
  
  let favorites = getFavorites();
  if (favorites.includes(currentSong.id)) {
    favorites = favorites.filter(id => id !== currentSong.id);
  } else {
    favorites.push(currentSong.id);
  }
  localStorage.setItem('assignment_favorites', JSON.stringify(favorites));
  updateFavoriteUI(currentSong.id);
  
  if (activeTab === 'favorites') {
    renderList();
  }
});

// Recently Played
function getRecent() {
  return JSON.parse(localStorage.getItem('assignment_recent') || '[]');
}

function addToRecent(songId) {
  let recent = getRecent();
  // Remove if already exists so we can move it to top
  recent = recent.filter(id => id !== songId);
  recent.unshift(songId);
  
  // Cap at 15 items
  if (recent.length > 15) {
    recent.pop();
  }
  
  localStorage.setItem('assignment_recent', JSON.stringify(recent));
  if (activeTab === 'recent') {
    renderList();
  }
}

// Search
searchInput.addEventListener('input', () => {
  const query = searchInput.value.toLowerCase().trim();
  filteredSongs = songs.filter(song => 
    song.title.toLowerCase().includes(query) || 
    song.artist.toLowerCase().includes(query)
  );
  if (activeTab !== 'playlist') {
    switchTab('playlist', tabPlaylist);
  } else {
    renderList();
  }
});

// Voice Search (Web Speech API)
const SpeechRecognition = window.SpeechRecognition || window.webkitSpeechRecognition;

if (SpeechRecognition) {
  const recognition = new SpeechRecognition();
  recognition.lang = 'en-US';
  
  voiceSearchBtn.addEventListener('click', () => {
    voiceModal.classList.remove('hidden');
    recognition.start();
  });
  
  recognition.addEventListener('result', (event) => {
    const transcript = event.results[0][0].transcript;
    searchInput.value = transcript;
    
    // Trigger search filter
    filteredSongs = songs.filter(song => 
      song.title.toLowerCase().includes(transcript.toLowerCase()) ||
      song.artist.toLowerCase().includes(transcript.toLowerCase())
    );
    
    if (activeTab !== 'playlist') {
      switchTab('playlist', tabPlaylist);
    } else {
      renderList();
    }
    
    setTimeout(() => {
      voiceModal.classList.add('hidden');
    }, 1000);
  });
  
  recognition.addEventListener('end', () => {
    voiceModal.classList.add('hidden');
  });

  closeVoiceBtn.addEventListener('click', () => {
    recognition.stop();
    voiceModal.classList.add('hidden');
  });
} else {
  voiceSearchBtn.style.display = 'none';
  voiceStatus.textContent = 'Voice Search not supported in this browser.';
}
// Visitor Counter
let visitorCount = localStorage.getItem('visitorCount') || 0;

visitorCount++;

localStorage.setItem('visitorCount', visitorCount);

document.getElementById('visitor-count').textContent = visitorCount;