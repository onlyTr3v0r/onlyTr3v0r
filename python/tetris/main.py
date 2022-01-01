from typing_extensions import runtime
import pygame
from pygame.locals import (
    QUIT,
    KEYDOWN,
    K_ESCAPE,
    K_LEFT,
    K_RIGHT
)

BLOCK_DIMENSIONS = (40,40)
SCREEN_WIDTH = 300
SCREEN_HEIGHT = 600
SPEEED = 1

class Block(pygame.sprite.Sprite):
    def __init__(self):
        super(Block, self).__init__()
        self.surf = pygame.Surface(BLOCK_DIMENSIONS)
        self.surf.fill((0,0,0))
        self.rect = self.surf.get_rect()

    def update(self):
        keys = pygame.key.get_pressed()
        if keys[K_LEFT]:
            self.rect.move_ip(-SPEEED, 0)
        if keys[K_RIGHT]:
            self.rect.move_ip(SPEEED, 0)

        if self.rect.left < 0: self.rect.left = 0
        if self.rect.right > SCREEN_WIDTH: self.rect.right = SCREEN_WIDTH
        if self.rect.top >= 0: self.rect.top = 0
        if self.rect.bottom >= SCREEN_HEIGHT: self.rect.bottom = SCREEN_HEIGHT

pygame.init()
pygame.display.set_caption("Tetris remake.")
display = pygame.display.set_mode((SCREEN_WIDTH, SCREEN_HEIGHT))

activeBlock = Block()

running = True
while running:
    for event in pygame.event.get():
        if event.type == KEYDOWN and event.key == K_ESCAPE: running = False
        if event.type == QUIT: running = False

    activeBlock.update()

    display.fill((255,255,255))
    display.blit(activeBlock.surf, activeBlock.rect)

    pygame.display.flip()