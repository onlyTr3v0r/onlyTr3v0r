from pathlib import Path
from time import time
from PIL import Image
import pygame

from pygame.locals import (K_SPACE, K_r, KEYDOWN, K_LEFT, K_RIGHT, QUIT)

class Animation:
    def __init__(self, path: Path | str, fps: int | float) -> None:
        if isinstance(path, str):
            path = Path(path)
        elif not isinstance(path, Path):
            raise TypeError(f"Argument path should be of type 'pathlib.Path' or 'str' not '{path.__class__.__name__}'")

        self.frames = get_gif_frames(path=str(path))
        self.index = 0
        self.frame = self.frames[self.index]
        self.tick_every = 1/float(fps)
        # self.time = time()
        self.c = 0

    def update(self) -> None:
        self.index = (self.index + 1) % len(self.frames)
        self.frame = self.frames[self.index]

# https://stackoverflow.com/a/63092962/17303382
def get_gif_frames(path: str) -> list[pygame.surface.Surface]:
    gif = Image.open(path)
    frames = []

    for index in range(gif.n_frames):
        gif.seek(index)

        pil_frame = gif.convert("RGBA")
        img_bytes, img_size, img_mode = pil_frame.tobytes(), pil_frame.size, pil_frame.mode
        pygame_frame = pygame.image.fromstring(img_bytes, img_size, img_mode)

        frames.append(pygame_frame)

    return frames

# gif = get_gif_frames("hedgehog.gif")
# count = 0

WIDTH, HEIGHT = SCR_DIM = Image.open("hedgehog.gif").size
FPS = 60

pygame.init()
screen = pygame.display.set_mode(SCR_DIM)
font = pygame.font.Font(pygame.font.get_default_font(), 12)
pygame.display.set_caption("Animation Testing")
clock = pygame.time.Clock()

animation = Animation("hedgehog.gif", 60)

individual = False
running = True
while running:
    screen.fill((255, 255, 255))

    pygame.display.set_caption(f"Animation Testing | FPS: {clock.get_fps():.2f}")

    # if not individual:
    #     count += 1

    for event in pygame.event.get():
        if event.type == QUIT:
            running = False
        if event.type == KEYDOWN:
            if event.key == K_SPACE:
                individual = not individual
            if event.key == K_r:
                count = 0
            if event.key == K_LEFT:
                count -= 1
            if event.key == K_RIGHT:
                count += 1

    mouse_pressed = pygame.mouse.get_pressed()
    if individual:
        if mouse_pressed[0]:
            count += 1
        if mouse_pressed[2]:
            count -= 1

    # count %= len(gif)
    # screen.blit(gif[count], (0, 0))
    animation.update()
    screen.blit(animation.frame, (0, 0))

    mode_text = font.render(f"Mode: {'Individual' if individual else 'Continous'}", True, (0, 0, 0))
    screen.blit(mode_text, (0, 0))

    count_text = font.render(f"Frame: {animation.index}", True, (0, 0, 0))
    screen.blit(count_text, (0, mode_text.get_height() + 5))

    pygame.display.flip()
    clock.tick(FPS)

pygame.quit()